package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;
import lapr.project.model.Container;
import lapr.project.model.Country;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContainerStore {

    private final List<Container> ContainerList = new ArrayList<>();

    private String data = "";

    private Container[][][] disp = new Container[10][10][10];


    public boolean addContainer(Container container){
        if (container == null){
            return false;
        }
        if (!this.ContainerList.contains(container))
            this.ContainerList.add(container);
        return true;
    }

    public Container getContainer(String containerId){
        for (Container c : ContainerList){
            if (c.getContainerId().equals(containerId)){
                return c;
            }
        }
        return null;
    }

    public List<Container> getContainerList() {
        return ContainerList;
    }

    public void fillArray () throws IOException {
        int x, y, z;
        for (Container c : App.getInstance().getCompany().getContainerStore().getContainerList()) {
            x = c.getX();
            y = c.getY();
            z = c.getZ();
            disp[x][y][z] = c;
        }

        for (int i = 0; i < disp.length; i++){
            for (int j = 0; j < disp[i].length; j++){
                for (int k = 0; k < disp[i][j].length; k++){
                    if (disp[i][k][j]!=null)
                        data+=String.format(disp[i][k][j].getContainerId() + " ");
                    else
                       data+=String.format(null  + " ");
                }
               data+=String.format("\t\t");
            }
            data+="\n";
        }

        FileOperation.writeToAFile("Output/ContainersDisposition.txt", data);
    }

    public Container[][][] getDisp() {
        return disp;
    }

}

