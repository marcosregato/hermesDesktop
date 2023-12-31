package controller;

import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import dao.UsuarioDao;
import model.Usuario;

public class PessoaController {
    
	static Logger logger = Logger.getLogger(PessoaController.class);

	UsuarioDao dao = new UsuarioDao();

	public void salvar(Usuario pessoa) {
		try {
			if(pessoa != null) {
				
				dao.salvar(pessoa);
			}else {
				// COLOCAR MENSAGEM
			}

		} catch (Exception e) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public void editar(Usuario pessoa) {
		try {
			if(pessoa != null) {
//				pessoa.setNome(txtNome.getText());
//				pessoa.setEndereco(txtEndereco.getText());
//				pessoa.setTelefone(txtTelefone.getText());
//				pessoa.setBairro(txtBairro.getText());
//				pessoa.setCep(txtCep.getText());
//				pessoa.setNome(txtMunicipio.getText());
//				pessoa.setEstado(comboEstado.getValue().toString());
//				pessoa.setEmail(txtEmail.getText());
				dao.editar(pessoa);
			}else {
				// COLOCAR MENSAGEM
			}

		} catch (Exception e) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public List<Usuario> listar() {
		try {

			return  dao.listar();

		} catch (Exception e) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
		}

		return Collections.emptyList();
	}

	public void delete(String nome) {
		try {
			
//			if(nome != null) {
//				if(result.get() == btConfirmar) {
//					dao.excluir(nome);
//				}
//			}

		} catch (Exception e) {
			logger.info( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

    public boolean acessoSistema(String login, String senha) {
        try {
            dao.buscarGrau(login, senha);
        } catch (Exception e) {
            logger.info( e.getClass().getName() + ": " + e.getMessage() );
        }
        return false;
    }
}
