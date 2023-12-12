package manipularArquivo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ManipularArquivo {

	private static String pathFileExcel = "C:\\workspace\\importDado\\src\\planilha_teste_2.xls";
	
	FileInputStream fs;
	Workbook wb;
	Sheet sh;


	public List<String> getListNomeAba(int quantidadeAba) {

		try {

			List<String> listNomeAba = new ArrayList<>();
			for(int aba =0; aba <= quantidadeAba; aba++) {
				
				fs = new FileInputStream(pathFileExcel);
				wb = Workbook.getWorkbook(fs);
				sh = wb.getSheet(aba);
				if(sh.getName()!=null)
					listNomeAba.add(sh.getName());
			}
			return listNomeAba;

		} catch (IOException | BiffException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<String> getListColuna(){
		try {
			List<String> listColuna = new ArrayList<>();
			fs = new FileInputStream(pathFileExcel);
			wb = Workbook.getWorkbook(fs);
			sh = wb.getSheet("TURMA");
			int count =3;
			for(int x=0; x < count;x++ ) {
				String celula = sh.getCell(x,0).getContents();
				if(celula!=null) {
					listColuna.add(celula);
				}
			}
			return listColuna;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();

	}

	public static void main(String [] asdf){
		ManipularArquivo m = new ManipularArquivo();
		System.out.println(m.getListNomeAba(1));
	}


}
