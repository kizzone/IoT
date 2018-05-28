/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclient;

/**
 *
 * @author domenico
 */
public class LevelPosition {
    
    private int XCord;
    private int YCord;
    private int ZCord;

    public int getXCord() {
        return XCord;
    }

    public void setXCord(int XCord) {
        this.XCord = XCord;
    }

    public int getYCord() {
        return YCord;
    }

    public void setYCord(int YCord) {
        this.YCord = YCord;
    }

    public int getZCord() {
        return ZCord;
    }

    public void setZCord(int ZCord) {
        this.ZCord = ZCord;
    }
    
    
    @Override
    public String toString()
    {
        return "Class Positions  [ZCord = "+ZCord+", YCord = "+YCord+", XCord = "+XCord+"]";
    }

    
}
