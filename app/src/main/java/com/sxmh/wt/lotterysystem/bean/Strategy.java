package com.sxmh.wt.lotterysystem.bean;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.gprinter.command.EscCommand;
import com.gprinter.command.LabelCommand;
import com.sxmh.wt.lotterysystem.view.SelectedBallInfo;

import java.util.List;

public interface Strategy {
    void initBox();

    SelectedBallInfo selectOneByMachine();

    List<SelectedBallInfo> insertToSelectedArea();

    void commitLottery(LinearLayout llAllBallInfo, Bundle params);

    EscCommand print();

    void resetWaitArea();

//    void printerNotify();
}
