package importaDado;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manipularArquivo.ManipularArquivo;
import util.Utils;

public class CriarTableSQL {
	
	

	//private static String pathFileExcel = "C:\\workspace\\importDado\\src\\arqSQL\\";
	private static String pathFileExcel = "C:\\workspace\\importDado\\src\\";
	
	
//	public CriarTableSQL(String path) {
//		this.pathFileExcel = path;
//	}
	
	Utils utils = new Utils();

	public String criarArquivoSQL(String arquivo) {
		try {
			File arq;
			File directoryPath = new File(pathFileExcel);
			File[] filesList = directoryPath.listFiles();
			for(int x =0; x < filesList.length;x++) {
				if(String.valueOf(filesList[x]).contains(arquivo)) {
					System.out.println("ARQUIVO EXISTE");

				}else {
					arq	= new File(pathFileExcel+arquivo);
					arq.createNewFile();
					return String.valueOf(arq);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

	public void criarTabela(String pathArqSQL, List<String> listNomeTabela){
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(pathArqSQL);

			for(int x =0; x < listNomeTabela.size();x++) {
				String nomeTabela = listNomeTabela.get(x); 
				ManipularArquivo m = new ManipularArquivo();
				
				List<String> atributo =new ArrayList<>();
				for(int s =0; s < m.getListColuna().size();s++) {
					atributo.add(m.getListColuna().get(s)+" varchar(50),\n");
				}
				String tabela = "create table "+nomeTabela+"(\n id int PRIMARY KEY NOT NULL AUTO_INCREMENT,"
						+ "\n"+atributo+"\n";
				tabela += addForeignKey("id_"+nomeTabela,nomeTabela+"(id)");
				tabela += "\n\n),\n";
				myWriter.write(utils.limpaQuery(tabela));
			}
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String addForeignKey(String id, String referencia) {
		return " FOREIGN KEY ("+ id +") REFERENCES "+referencia+"";
	}
	



	public static void main(String[] asdf) {
		ManipularArquivo ma = new ManipularArquivo();
		CriarTableSQL sql = new CriarTableSQL();
		sql.criarTabela(sql.criarArquivoSQL("teste.sql"),ma.getListNomeAba(1));
	}

}
