package com.empresa.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.empresa.struts.models.Usuario;
import com.empresa.struts.models.dto.SituacaoExecucaoDTO;
import com.empresa.struts.util.DBUtil;

public class UsuarioDAO {

	private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

	public List<Usuario> listarUsuarios(int pagina, int qtdPagina, String nmLogin, String nmRole) {
		List<Usuario> usuarios = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();

			StringBuilder query = new StringBuilder("SELECT * FROM usuario WHERE 1=1");

			List<Object> parametros = buildWhereCondition(nmLogin, nmRole, query);

			query.append(" LIMIT ?, ?");
			parametros.add((pagina - 1) * qtdPagina);
			parametros.add(qtdPagina);

			preparedStatement = connection.prepareStatement(query.toString());

			for (int i = 0; i < parametros.size(); i++) {
				preparedStatement.setObject(i + 1, parametros.get(i));
			}

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setNmLogin(resultSet.getString("nm_login"));
				usuario.setQtTempoInatividade(resultSet.getInt("qt_tempo_inatividade"));
				usuario.setIsPasswordEncripted(true);
				usuarios.add(usuario);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return usuarios;
	}

	private List<Object> buildWhereCondition(String nmLogin, String nmRole, StringBuilder query) {
		List<Object> parametros = new ArrayList<>();
		if (nmLogin != null && !nmLogin.isEmpty()) {
			query.append(" AND nm_login LIKE ?");
			parametros.add("%" + nmLogin.toLowerCase() + "%");
		}

		if (nmRole != null && !nmRole.isEmpty()) {
			query.append(" AND nm_role = ?");
			parametros.add(nmRole);
		}
		return parametros;
	}

	public boolean hasUsuarios() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			String query = "SELECT 1 FROM usuario LIMIT 1";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public int contarUsuarios(String nmLogin, String nmRole) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			StringBuilder query = new StringBuilder("SELECT COUNT(nm_login) FROM usuario WHERE 1=1");

			List<Object> parametros = buildWhereCondition(nmLogin, nmRole, query);

			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(query.toString());

			for (int i = 0; i < parametros.size(); i++) {
				preparedStatement.setObject(i + 1, parametros.get(i));
			}

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();

			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public SituacaoExecucaoDTO inserirUsuario(Usuario usuario) {
		logger.info("inserirUsuario");

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Usuario usuarioDb = getByLogin(usuario.getNmLogin());

			if (usuarioDb != null)
				return new SituacaoExecucaoDTO(false, "Usuário existente.");

			connection = DBUtil.getConnection();
			String query = "INSERT INTO avaliacao.usuario (nm_login, ds_senha, qt_tempo_inatividade) VALUES (?, ?, ?)";
			statement = connection.prepareStatement(query);

			statement.setString(1, usuario.getNmLogin().toLowerCase());
			statement.setString(2, usuario.getEncryptedPassword());
			statement.setInt(3, usuario.getQtTempoInatividade());

			int rowsInserted = statement.executeUpdate();

			return new SituacaoExecucaoDTO(rowsInserted > 0, "Usuário cadastrado com sucesso.");
		} catch (Exception e) {
			logger.info("inserirUsuario.exception");
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new SituacaoExecucaoDTO(false, "Ocorreu um erro inexperado.");
	}

	public Usuario getByLogin(String nmLogin) {
		logger.info("getByLogin");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Usuario usuario = null;

		try {
			connection = DBUtil.getConnection();
			String query = "SELECT * FROM avaliacao.usuario WHERE nm_login = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nmLogin);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				usuario = new Usuario();
				usuario.setNmLogin(resultSet.getString("nm_login"));
				usuario.setDsSenha(resultSet.getString("ds_senha"));
				usuario.setQtTempoInatividade(resultSet.getInt("qt_tempo_inatividade"));
				usuario.setIsPasswordEncripted(true);
			}

			connection.close();
		} catch (Exception e) {
			logger.info("getByLogin.exception");
			e.printStackTrace();
		}

		return usuario;
	}

	public boolean alterarUsuario(Usuario usuario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			

			Usuario usuarioDb = getByLogin(usuario.getNmLogin());

			if (usuarioDb != null)
				return false;
			
			connection = DBUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE usuario SET qt_tempo_inatividade=? WHERE nm_login=?");

			preparedStatement.setInt(1, usuario.getQtTempoInatividade());
			preparedStatement.setString(2, usuario.getNmLogin());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean excluirUsuario(String nmLogin) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM usuario WHERE nm_login = ?");
			preparedStatement.setString(1, nmLogin.toLowerCase());

			int rowsAffected = preparedStatement.executeUpdate();

			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}
