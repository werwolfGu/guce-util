package com.guce.designPattern.commandPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MacroCommand  implements Command{
    private List<Command> list = new ArrayList<Command>();

    public  void setList(List<Command> list){
        this.list = list;
    }
    public void execute() {
        for(Command command : list){
            command.execute();
        }
    }
}
