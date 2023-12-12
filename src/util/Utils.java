package util;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;




public class Utils {

	Logger log = LogManager.getLogger(Utils.class.getName());

	public String addTipoAtributo(String variavel, int valor) {

		//		Class<?> objectClass = variavel.getClass();
		//	    String className = objectClass.getSimpleName();
		//	    System.out.println(">>> " + className);

		if(variavel.contains("-")||variavel.contains("/")) {
			return "DATE,";
		}else if (variavel.contains(",") || variavel.contains(".")) {
			return "FLOAT,";

		} else if (variavel!=null && valor >0) {
			return "VARCHAR("+String.valueOf(valor)+")";
		}
		return null;
	}

	public String limpaQuery(String query) {
		return query.replace("[", " ").replace("]", "").replace(", ", " ");
	}

	public int countColunaExcel(String arq) {
		try {

			FileInputStream fileInputStream = new FileInputStream(arq);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet sh = workbook.getSheet("TURMA");
			int nColuna = sh.getRow(0).getPhysicalNumberOfCells();
			return nColuna;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("resource")
	public void countLinhaExcel(String arq) {
		try {
			
			InputStream ExcelFileToRead = new FileInputStream(arq);
			HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

			HSSFSheet sheet=wb.getSheetAt(0);
			HSSFRow row; 
			HSSFCell cell;

			Iterator rows = sheet.rowIterator();

			while (rows.hasNext()){
				row =(HSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				
				
				while (cells.hasNext()){
					cell=(HSSFCell) cells.next();
			
					if (cell.getCellType() == CellType.STRING){
						//listaDado.add(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue()+" ");
					}else if(cell.getCellType() == CellType.NUMERIC){
						//listaDado.add(String.valueOf(cell.getNumericCellValue()));
						System.out.print(cell.getNumericCellValue()+" ");
					}else{
						//U Can Handel Boolean, Formula, Errors
					}
				}
				System.out.println();
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void main(String ... adf) {
		Utils t = new Utils();
		String pathFileExcel = "C:\\workspace\\importDado\\src\\planilha_teste_2.xls";
		t.countLinhaExcel(pathFileExcel);
	}
}
