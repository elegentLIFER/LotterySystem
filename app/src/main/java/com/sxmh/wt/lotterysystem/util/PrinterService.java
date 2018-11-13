package com.sxmh.wt.lotterysystem.util;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sxmh.wt.lotterysystem.R;
import com.sxmh.wt.lotterysystem.printer.DeviceConnFactoryManager;
import com.sxmh.wt.lotterysystem.printer.PrinterCommand;
import com.sxmh.wt.lotterysystem.printer.ThreadPool;
import com.sxmh.wt.lotterysystem.printer.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_DETACHED;
import static com.sxmh.wt.lotterysystem.printer.Constant.ACTION_USB_PERMISSION;
import static com.sxmh.wt.lotterysystem.printer.Constant.MESSAGE_UPDATE_PARAMETER;
import static com.sxmh.wt.lotterysystem.printer.DeviceConnFactoryManager.ACTION_QUERY_PRINTER_STATE;
import static com.sxmh.wt.lotterysystem.printer.DeviceConnFactoryManager.CONN_STATE_FAILED;

public class PrinterService extends Service {
    private static final String TAG = "PrinterService";
    private PrinterBinder binder;

    // 判断打印机所使用指令是否是ESC指令
    protected int id = 0;
    private static final int STATE_DISCONNECT = 0x007;
    private static final int PRINTER_COMMAND_ERROR = 0x008;
    private static final int CONN_PRINTER = 0x12;

    private boolean isDeviceConnected;
    private ThreadPool threadPool;
    private UsbManager usbManager;


    private static final String DEBUG_TAG = "DeviceListActivity";
    public static LinearLayout deviceNamelinearLayout;

    private ListView lvPairedDevice = null, lvNewDevice = null;
    private TextView tvPairedDevice = null, tvNewDevice = null;
    private Button btDeviceScan = null;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;
    private ArrayAdapter<String> mNewDevicesArrayAdapter;
    public static final String EXTRA_DEVICE_ADDRESS = "address";
    public static final int REQUEST_ENABLE_BT = 2;
    public static final int REQUEST_CONNECT_DEVICE = 3;

    AlertDialog alertDialog;

    public class PrinterBinder extends Binder {
        private boolean connectByBlueTooth;

        public PrinterBinder() {
        }

        public void connect() {
            if (connectByBlueTooth) {
                View inflate = LayoutInflater.from(PrinterService.this).inflate(R.layout.dialog_bluetooth_list, null);
                tvPairedDevice = inflate.findViewById(R.id.tvPairedDevices);
                lvPairedDevice = inflate.findViewById(R.id.lvPairedDevices);
                tvNewDevice = inflate.findViewById(R.id.tvNewDevices);
                lvNewDevice = inflate.findViewById(R.id.lvNewDevices);
                btDeviceScan = inflate.findViewById(R.id.btBluetoothScan);
                btDeviceScan.setOnClickListener((View v) -> {
                    v.setVisibility(View.GONE);
                    discoveryDevice();
                });
                alertDialog = new AlertDialog.Builder(PrinterService.this).setView(inflate).create();
                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                alertDialog.show();
                initBluetooth();
            } else {
                getUsbDeviceList();
            }
        }

        public void print(Vector<Byte> datas) {
            btnReceiptPrint(datas);
        }

        public boolean isDeviceConnected() {
            return isDeviceConnected;
        }

        public void setConnectByBlueTooth(boolean connectByBlueTooth) {
            this.connectByBlueTooth = connectByBlueTooth;
        }

        public void btnPrinterState() {
            PrinterService.this.btnPrinterState();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(ACTION_USB_DEVICE_DETACHED);
        filter.addAction(ACTION_QUERY_PRINTER_STATE);
        filter.addAction(DeviceConnFactoryManager.ACTION_CONN_STATE);
        registerReceiver(receiver, filter);

        IntentFilter intentFilter = new IntentFilter(Constants.ACTION);
        registerReceiver(localReceiver, intentFilter);

        binder = new PrinterBinder();
        usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);


        // Register for broadcasts when a device is discovered
        IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mFindBlueToothReceiver, filter1);
        // Register for broadcasts when discovery has finished
        filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mFindBlueToothReceiver, filter1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(localReceiver);

        DeviceConnFactoryManager.closeAllPort();
        if (threadPool != null) {
            threadPool.stopThreadPool();
        }
    }

    private BroadcastReceiver localReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReceive: " + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            boolean canPrint = intent.getBooleanExtra(Constants.PREINTER_STATUS, false);
            ToastUtil.newToast(PrinterService.this, "这里：：：：  " + canPrint);
        }
    };

    public void btnReceiptPrint(Vector<Byte> datas) {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null ||
                !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
            Utils.toast(this, getString(R.string.str_cann_printer));
            return;
        }
        threadPool = ThreadPool.getInstantiation();
        threadPool.addTask(() -> {
            if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.ESC) {
//                EscCommand esc = new EscCommand();
//                esc.addInitializePrinter();
//                esc.addQueryPrinterStatus();
//                Vector<Byte> command = esc.getCommand();
//                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null) return;
//                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(command);
                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null) return;
                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
            } else {
                printerHandler.obtainMessage(PRINTER_COMMAND_ERROR).sendToTarget();
            }
        });
    }

    public void getUsbDeviceList() {
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> devices = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = devices.values().iterator();
        int count = devices.size();
        if (count > 0) {
            while (deviceIterator.hasNext()) {
                UsbDevice device = deviceIterator.next();
                String devicename = device.getDeviceName();
                if (checkUsbDevicePidVid(device)) {
                    connectDevice(devicename);
                }
            }
        }
    }

    private void connectDevice(String deviceName) {
        //通过USB设备名找到USB设备
        UsbDevice usbDevice = Utils.getUsbDeviceFromName(this, deviceName);
        //判断USB设备是否有权限
        if (usbManager.hasPermission(usbDevice)) {
            usbConn(usbDevice);
        } else {//请求权限
            PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
            usbManager.requestPermission(usbDevice, mPermissionIntent);
        }
    }

    boolean checkUsbDevicePidVid(UsbDevice dev) {
        int pid = dev.getProductId();
        int vid = dev.getVendorId();
        return ((vid == 34918 && pid == 256) || (vid == 1137 && pid == 85)
                || (vid == 6790 && pid == 30084)
                || (vid == 26728 && pid == 256) || (vid == 26728 && pid == 512)
                || (vid == 26728 && pid == 256) || (vid == 26728 && pid == 768)
                || (vid == 26728 && pid == 1024) || (vid == 26728 && pid == 1280)
                || (vid == 26728 && pid == 1536));
    }

    /**
     * usb连接
     *
     * @param usbDevice
     */
    private void usbConn(UsbDevice usbDevice) {
        new DeviceConnFactoryManager.Build()
                .setId(id)
                .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.USB)
                .setUsbDevice(usbDevice)
                .setContext(this)
                .build();
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_USB_PERMISSION:
                    synchronized (this) {
                        UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            if (device != null) {
                                Log.e(TAG, "permission ok for device " + device);
                                usbConn(device);
                            }
                        } else {
                            Log.e(TAG, "permission denied for device " + device);
                        }
                    }
                    break;
                // Usb连接断开、蓝牙连接断开广播
                case ACTION_USB_DEVICE_DETACHED:
                    ToastUtil.newToast(PrinterService.this, "USB断开");
                    isDeviceConnected = false;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    printerHandler.obtainMessage(STATE_DISCONNECT).sendToTarget();
                    break;
                case DeviceConnFactoryManager.ACTION_CONN_STATE:
                    int state = intent.getIntExtra(DeviceConnFactoryManager.STATE, -1);
                    int deviceId = intent.getIntExtra(DeviceConnFactoryManager.DEVICE_ID, -1);
                    switch (state) {
                        case DeviceConnFactoryManager.CONN_STATE_DISCONNECT:
                            if (id == deviceId) {
                                Utils.toast(PrinterService.this, "打印机断开连接");
                                isDeviceConnected = false;
                            }
                            break;
                        case DeviceConnFactoryManager.CONN_STATE_CONNECTING:
                            break;
                        case DeviceConnFactoryManager.CONN_STATE_CONNECTED:
                            Utils.toast(PrinterService.this, "打印机连接成功");
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            isDeviceConnected = true;
                            break;
                        case CONN_STATE_FAILED:
                            Utils.toast(PrinterService.this, "打印机连接失败");
                            isDeviceConnected = false;
                            break;
                        default:
                            break;
                    }
                    break;
                case DeviceConnFactoryManager.ACTION_HH:
                    boolean state1 = intent.getBooleanExtra(DeviceConnFactoryManager.ACTION_HH, false);
                    ToastUtil.newToast(PrinterService.this, state1 + "");
                    break;
                default:
                    break;
            }
        }
    };

    @SuppressWarnings("handlerleak")
    private Handler printerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STATE_DISCONNECT:
                    if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].closePort(id);
                    }
                    break;
                case PRINTER_COMMAND_ERROR:
                    Utils.toast(PrinterService.this, "命令错误");
                    break;
                case CONN_PRINTER:
                    Utils.toast(PrinterService.this, "连接打印机");
                    break;
                case MESSAGE_UPDATE_PARAMETER:
                    String strIp = msg.getData().getString("Ip");
                    String strPort = msg.getData().getString("Port");
                    //初始化端口信息
                    new DeviceConnFactoryManager.Build()
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI) //设置端口连接方式
                            .setIp(strIp)//设置端口IP地址
                            .setId(id)//设置端口ID（主要用于连接多设备）
                            .setPort(Integer.parseInt(strPort))//设置连接的热点端口号
                            .build();
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask(() -> DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort());
                    break;
                default:
                    break;
            }
        }
    };

    private void initBluetooth() {
        // Get the local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Utils.toast(this, "Bluetooth is not supported by the device");
        } else {
            // If BT is not on, request that it be enabled.
            // setupChat() will then be called during onActivityResult
//            if (!mBluetoothAdapter.isEnabled()) {
//                Intent enableIntent = new Intent(
//                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                startActivityForResult(enableIntent,
//                        REQUEST_ENABLE_BT);
//            } else {
            getDeviceList();
//            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_ENABLE_BT) {
//            if (resultCode == Activity.RESULT_OK) {
//                // bluetooth is opened
//                getDeviceList();
//            } else {
//                // bluetooth is not open
//                Toast.makeText(this, "蓝牙未开启",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    protected void getDeviceList() {
        // Initialize array adapters. One for already paired devices and
        // one for newly discovered devices
        mPairedDevicesArrayAdapter = new ArrayAdapter<>(this,
                R.layout.bluetooth_device_name_item);
        mNewDevicesArrayAdapter = new ArrayAdapter<>(this,
                R.layout.bluetooth_device_name_item);
        lvPairedDevice.setAdapter(mPairedDevicesArrayAdapter);
        lvPairedDevice.setOnItemClickListener(mDeviceClickListener);
        lvNewDevice.setAdapter(mNewDevicesArrayAdapter);
        lvNewDevice.setOnItemClickListener(mDeviceClickListener);
//        // Get the local Bluetooth adapter
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            tvPairedDevice.setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n"
                        + device.getAddress());
            }
        } else {
            String noDevices = "没有已匹配的设备";
            mPairedDevicesArrayAdapter.add(noDevices);
        }
    }

    /**
     * changes the title when discovery is finished
     */
    private final BroadcastReceiver mFindBlueToothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed
                // already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevicesArrayAdapter.add(device.getName() + "\n"
                            + device.getAddress());
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                Log.i("tag", "finish discovery" + mNewDevicesArrayAdapter.getCount());
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = "未发现蓝牙设备";
                    mNewDevicesArrayAdapter.add(noDevices);
                }
            }
        }
    };

    private void discoveryDevice() {
        // Indicate scanning in the title
        // Turn on sub-title for new devices
        tvNewDevice.setVisibility(View.VISIBLE);

        lvNewDevice.setVisibility(View.VISIBLE);
        // If we're already discovering, stop it
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        // Request discover from BluetoothAdapter
        mBluetoothAdapter.startDiscovery();
    }

    AlertDialog dialog;

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // Cancel discovery because it's costly and we're about to connect
            mBluetoothAdapter.cancelDiscovery();
            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String noDevices = "没有已匹配的设备";
            String noNewDevice = "未找到设备";
            if (!info.equals(noDevices) && !info.equals(noNewDevice)) {
                alertDialog.dismiss();

                dialog = new AlertDialog.Builder(PrinterService.this).setTitle("正在连接").create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();

                ToastUtil.newToast(PrinterService.this, "连接中.....");
                String address = info.substring(info.length() - 17);
                // Create the result Intent and include the MAC address
                //初始化话DeviceConnFactoryManager
                new DeviceConnFactoryManager.Build()
                        .setId(id)
                        //设置连接方式
                        .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH)
                        //设置连接的蓝牙mac地址
                        .setMacAddress(address)
                        .build();
                //打开端口
                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
            }
        }
    };

    /**
     * ESC查询打印机实时状态指令
     */
    private byte[] esc = {0x10, 0x04, 0x02};
    /**
     * CPCL查询打印机实时状态指令
     */
    private byte[] cpcl = {0x1b, 0x68};
    /**
     * TSC查询打印机状态指令
     */
    private byte[] tsc = {0x1b, '!', '?'};

    private void btnPrinterState() {
        //打印机状态查询
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null ||
                !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
            Utils.toast(this, getString(R.string.str_cann_printer));
            return;
        }
        ThreadPool.getInstantiation().addTask(new Runnable() {
            @Override
            public void run() {
                Vector<Byte> data = new Vector<>(esc.length);
                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.ESC) {
                    for (int i = 0; i < esc.length; i++) {
                        data.add(esc[i]);
                    }
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(data);
                } else if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.TSC) {
                    for (int i = 0; i < tsc.length; i++) {
                        data.add(tsc[i]);
                    }
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(data);
                } else if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.CPCL) {
                    for (int i = 0; i < cpcl.length; i++) {
                        data.add(cpcl[i]);
                    }
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(data);
                }
            }
        });
    }
}
