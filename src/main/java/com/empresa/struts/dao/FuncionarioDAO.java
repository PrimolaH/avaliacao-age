package com.empresa.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.empresa.struts.models.Funcionario;
import com.empresa.struts.util.DBUtil;

public class FuncionarioDAO {

	private static final Logger logger = Logger.getLogger(FuncionarioDAO.class.getName());

	public Funcionario getFuncionarioById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM funcionario WHERE cd_funcionario = ?");
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCdFuncionario(rs.getInt("cd_funcionario"));
				funcionario.setNmFuncionario(rs.getString("nm_funcionario"));
				return funcionario;
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

		return null;
	}

	public List<Funcionario> listarFuncionarios(int pagina, int qtdPagina, String nm_funcionario,
			Integer cd_funcionario) {
		logger.info("pagina: " + pagina);
		logger.info("qtdPagina: " + qtdPagina);

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			StringBuilder query = new StringBuilder("SELECT cd_funcionario, nm_funcionario FROM funcionario WHERE 1=1");

			List<Object> parametros = buildWhereCondition(nm_funcionario, cd_funcionario, query);

			query.append(" LIMIT ?, ?");
			parametros.add((pagina - 1) * qtdPagina);
			parametros.add(qtdPagina);

			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(query.toString());

			for (int i = 0; i < parametros.size(); i++) {
				preparedStatement.setObject(i + 1, parametros.get(i));
			}

			ResultSet rs = preparedStatement.executeQuery();

			List<Funcionario> funcionarios = new ArrayList<>();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCdFuncionario(rs.getInt("cd_funcionario"));
				funcionario.setNmFuncionario(rs.getString("nm_funcionario"));
				funcionarios.add(funcionario);
			}

			return funcionarios;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Funcionario>();
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

	public List<Funcionario> listarFuncionarios() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			StringBuilder query = new StringBuilder(
					"SELECT cd_funcionario, nm_funcionario FROM funcionario ORDER BY nm_funcionario");
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(query.toString());

			ResultSet rs = preparedStatement.executeQuery();

			List<Funcionario> funcionarios = new ArrayList<>();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCdFuncionario(rs.getInt("cd_funcionario"));
				funcionario.setNmFuncionario(rs.getString("nm_funcionario"));
				funcionarios.add(funcionario);
			}

			return funcionarios;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Funcionario>();
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

	private List<Object> buildWhereCondition(String nm_funcionario, Integer cd_funcionario, StringBuilder query) {
		List<Object> parametros = new ArrayList<>();

		if (nm_funcionario != null && !nm_funcionario.isEmpty()) {
			query.append(" AND nm_funcionario LIKE ?");
			parametros.add("%" + nm_funcionario.toUpperCase().trim() + "%");
		}

		if (cd_funcionario != null && cd_funcionario > 0) {
			query.append(" AND cd_funcionario = ?");
			parametros.add(cd_funcionario);
		}

		return parametros;
	}

	public int contarFuncionarios(String nm_funcionario, Integer cd_funcionario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			StringBuilder query = new StringBuilder("SELECT COUNT(cd_funcionario) FROM funcionario WHERE 1=1");
			List<Object> parametros = buildWhereCondition(nm_funcionario, cd_funcionario, query);

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

	public int incluirFuncionario(Funcionario funcionario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();

			preparedStatement = connection
					.prepareStatement("SELECT cd_funcionario from funcionario order by cd_funcionario DESC LIMIT 1");
			ResultSet rs = preparedStatement.executeQuery();
			int id = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					id = rs.getInt("cd_funcionario");
				}
			}
			

			preparedStatement = connection.prepareStatement(
					"INSERT INTO funcionario (cd_funcionario, nm_funcionario) VALUES (?, ?)");

			preparedStatement.setLong(1, ++id);
			preparedStatement.setString(2, funcionario.getNmFuncionario());

			int rowsInserted = preparedStatement.executeUpdate();

			if (rowsInserted > 0) {
					return id;
			}

			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
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

	public boolean alterarFuncionario(Funcionario funcionario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE funcionario SET nm_funcionario=? WHERE cd_funcionario=?");

			preparedStatement.setString(1, funcionario.getNmFuncionario());
			preparedStatement.setInt(2, funcionario.getCdFuncionario());

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

	public boolean excluirFuncionario(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM funcionario WHERE cd_funcionario = ?");
			preparedStatement.setInt(1, id);

			int rowsAffected = preparedStatement.executeUpdate();

			return rowsAffected > 0;
		} catch (SQLIntegrityConstraintViolationException e) {
        	if (e.getMessage().contains("Cannot delete or update a parent row")) {
        		throw new IllegalArgumentException("Não é possível excluir um funcionario que já foi utilizado");
        	}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		return false;
	}
}


