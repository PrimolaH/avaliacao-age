package com.empresa.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.empresa.struts.models.ExameRealizado;
import com.empresa.struts.util.DBUtil;

public class ExameRealizadoDAO {
	
	    
    public List<ExameRealizado> listarExamesRealizados(int pagina, int qtdPagina, String nmExame, String nmFuncionario) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            StringBuilder query = new StringBuilder("SELECT er.cd_funcionario, er.cd_exame, er.dt_realizacao, e.nm_exame, f.nm_funcionario FROM exame_realizado er");
            query.append(" JOIN exame e ON er.cd_exame = e.cd_exame");
            query.append(" JOIN funcionario f ON er.cd_funcionario = f.cd_funcionario"); 
            query.append(" WHERE 1=1");
            
            List<Object> parametros = buildWhereCondition(nmExame, nmFuncionario, query);

            query.append(" LIMIT ?, ?");
            parametros.add((pagina - 1) * qtdPagina);
            parametros.add(qtdPagina);

            preparedStatement = connection.prepareStatement(query.toString());            

            for (int i = 0; i < parametros.size(); i++) {
                preparedStatement.setObject(i + 1, parametros.get(i));
            }            

            ResultSet rs = preparedStatement.executeQuery();

            List<ExameRealizado> examesRealizados = new ArrayList<>();
            while (rs.next()) {
                ExameRealizado exameRealizado = new ExameRealizado();
                exameRealizado.setCd_funcionario(rs.getInt("cd_funcionario"));
                exameRealizado.setCd_exame(rs.getInt("cd_exame"));
                exameRealizado.setDt_realizacao(rs.getDate("dt_realizacao"));
                exameRealizado.setNm_exame(rs.getString("nm_exame"));
                exameRealizado.setNm_funcionario(rs.getString("nm_funcionario"));
                examesRealizados.add(exameRealizado);
            }

            return examesRealizados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<ExameRealizado> listarExamesPorData(int pagina, int qtdPagina, Date dataInicio, Date dataFim) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            StringBuilder query = new StringBuilder("SELECT er.cd_funcionario, er.cd_exame, er.dt_realizacao, e.nm_exame, f.nm_funcionario FROM exame_realizado er");
            query.append(" JOIN exame e ON er.cd_exame = e.cd_exame");
            query.append(" JOIN funcionario f ON er.cd_funcionario = f.cd_funcionario"); 
            query.append(" WHERE er.dt_realizacao >= ? AND er.dt_realizacao <= ?");

            query.append(" LIMIT ?, ?");
            
            preparedStatement = connection.prepareStatement(query.toString());            
            preparedStatement.setDate(1, new java.sql.Date(dataInicio.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(dataFim.getTime()));
            preparedStatement.setInt(3, (pagina - 1) * qtdPagina);
            preparedStatement.setInt(4, qtdPagina);

            ResultSet rs = preparedStatement.executeQuery();

            List<ExameRealizado> examesRealizados = new ArrayList<>();
            while (rs.next()) {
                ExameRealizado exameRealizado = new ExameRealizado();
                exameRealizado.setCd_funcionario(rs.getInt("cd_funcionario"));
                exameRealizado.setCd_exame(rs.getInt("cd_exame"));
                exameRealizado.setDt_realizacao(rs.getDate("dt_realizacao"));
                exameRealizado.setNm_exame(rs.getString("nm_exame"));
                exameRealizado.setNm_funcionario(rs.getString("nm_funcionario"));
                examesRealizados.add(exameRealizado);
            }

            return examesRealizados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public int contarExamesRealizados(String nmExame, String nmFuncionario) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            
            StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM exame_realizado er");
            
            if (nmExame != null && !nmExame.isEmpty()) {
            	query.append(" JOIN exame e ON er.cd_exame = e.cd_exame");
            }
            
            if (nmFuncionario != null && !nmFuncionario.isEmpty()) {
            	query.append(" JOIN funcionario f ON er.cd_funcionario = f.cd_funcionario");
            }
            
            query.append(" WHERE 1=1");
            
            List<Object> parametros = buildWhereCondition(nmExame, nmFuncionario, query);

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
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public int contarExamesPorData(Date dataInicio, Date dataFim) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM exame_realizado er");
            query.append(" WHERE er.dt_realizacao >= ? AND er.dt_realizacao <= ?");

            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setDate(1, new java.sql.Date(dataInicio.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(dataFim.getTime()));

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    private List<Object> buildWhereCondition(String nmExame, String nmFuncionario, StringBuilder query) {
		List<Object> parametros = new ArrayList<>();
		
		if (nmExame != null && !nmExame.isEmpty()) {
		    query.append(" AND e.nm_exame LIKE ?");
		    parametros.add("%" + nmExame.toUpperCase().trim() + "%");
		}

		if (nmFuncionario != null && !nmFuncionario.isEmpty()) {
		    query.append(" AND f.nm_funcionario LIKE ?");
		    parametros.add("%" + nmFuncionario.toUpperCase().trim() + "%");
		}
		
		return parametros;
	}
    
    public int incluirExameRealizado(ExameRealizado exameRealizado) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO exame_realizado (cd_funcionario, cd_exame, dt_realizacao) VALUES (?, ?, ?)");

            preparedStatement.setInt(1, exameRealizado.getCd_funcionario());
            preparedStatement.setInt(2, exameRealizado.getCd_exame());
            preparedStatement.setDate(3, new java.sql.Date(exameRealizado.getDt_realizacao().getTime()));

            int rowsInserted = preparedStatement.executeUpdate();            
            
            return rowsInserted;
        } catch (SQLIntegrityConstraintViolationException e) {
        	if (e.getMessage().contains("Duplicate entry")) {
        		throw new IllegalArgumentException("Este exame já está cadastrado para este funcionário nesta data");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
        return -1;
    }

}