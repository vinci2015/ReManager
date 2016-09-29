package com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.handler;

/**
 * @author huangyouqiang
 * @date 2016/9/28
 */

public class NumberRecyclerHandler {
    private String content;
    private boolean isShow = true;

    public NumberRecyclerHandler(String content) {
        this.content = content;
        if(content.equals("null")){
            isShow = false;
        }
    }

    public String getContent() {
        return content;
    }
    public boolean getIsShow(){
        return isShow;
    }
}
