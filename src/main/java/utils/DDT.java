package utils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DDT {
    String path = System.getProperty("user.dir")+"//testdata//data.xlsx";
    String sheetName1 = "Sheet1";
    @DataProvider(name = "ValidData")
    public String[][] getLoginData() throws IOException{
        int ttlRows = ExcelOperation.getRowCount(path, sheetName1);
        int ttlCells = ExcelOperation.getCellCount(path, sheetName1,0);
        System.out.println(ttlRows +"::"+ttlCells);
        String[][] testData = new String[ttlRows-1][ttlCells];
        for(int i=1;i<ttlRows;i++) {
            for(int j =0;j<ttlCells;j++) {
                testData[i-1][j] = ExcelOperation.getCellValue(path, sheetName1, i, j);
            }
        }
        for(int i=0;i<testData.length;i++) {
            for(int j =0;j<testData[i].length;j++) {
                System.out.println(testData[i][j]+"|");
            }
        }

        return testData;
    }
    @DataProvider(name="commonData")
	public String[][] getData(){
		String [][] data = new String[1][2];
		data[0][0]="standard_user";
		data[0][1]="secret_sauce";		
		return data;		
	}
}

