package com.sxmh.wt.lotterysystem.bean;

import com.sxmh.wt.lotterysystem.bean.response.FindRuleResponse;

import java.io.Serializable;
import java.util.List;

public class GameRule implements Serializable{
    private int R001;
    private int R002;
    private int R003;
    private int R004;
    private int R005;
    private int R006;
    private int R007;
    private int R008;

    public GameRule() {
    }

//    public GameRule(List<FindRuleResponse.RuleListBean> ruleList) {
//        setData(ruleList);
//    }

    public void setData(List<FindRuleResponse.RuleListBean> ruleList) {
        resetData();
        int size = ruleList.size();
        for (int i = 0; i < size; i++) {
            FindRuleResponse.RuleListBean bean = ruleList.get(i);
            String ruleNumber = bean.getRuleNum();
            int number = Integer.valueOf(bean.getNumber());

            if ("R001".equals(ruleNumber)) {
                setR001(number);
            }else if ("R002".equals(ruleNumber)) {
                setR002(number);
            }else if ("R003".equals(ruleNumber)) {
                setR003(number);
            }else if ("R004".equals(ruleNumber)) {
                setR004(number);
            }else if ("R005".equals(ruleNumber)) {
                setR005(number);
            }else if ("R006".equals(ruleNumber)) {
                setR006(number);
            }else if ("R007".equals(ruleNumber)) {
                setR007(number);
            }else if ("R008".equals(ruleNumber)) {
                setR008(number);
            }
        }
    }

    private void resetData() {
        setR001(0);
        setR002(0);
        setR003(0);
        setR004(0);
        setR005(0);
        setR006(0);
        setR007(0);
        setR008(0);
    }

    public int getR001() {
        return R001;
    }

    public void setR001(int r001) {
        R001 = r001;
    }

    public int getR002() {
        return R002;
    }

    public void setR002(int r002) {
        R002 = r002;
    }

    public int getR003() {
        return R003;
    }

    public void setR003(int r003) {
        R003 = r003;
    }

    public int getR004() {
        return R004;
    }

    public void setR004(int r004) {
        R004 = r004;
    }

    public int getR005() {
        return R005;
    }

    public void setR005(int r005) {
        R005 = r005;
    }

    public int getR006() {
        return R006;
    }

    public void setR006(int r006) {
        R006 = r006;
    }

    public int getR007() {
        return R007;
    }

    public void setR007(int r007) {
        R007 = r007;
    }

    public int getR008() {
        return R008;
    }

    public void setR008(int r008) {
        R008 = r008;
    }
}
