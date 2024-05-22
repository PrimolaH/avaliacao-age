package com.empresa.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.empresa.struts.models.Exame;
import com.empresa.struts.util.DBUtil;

public class ExameDAO {
	
	private static final Logger logger = Logger.getLogger(ExameDAO.class.getName());
	
	public Exame getExameById(int id) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = DBUtil.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT * FROM exame WHERE cd_exame = ?");
	        preparedStatement.setInt(1, id);

	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            Exame exame = new Exame();
	            exame.setCdExame(rs.getInt("cd_exame"));
	            exame.setNmExame(rs.getString("nm_exame"));
	            exame.setIcAtivo(rs.getBoolean("ic_ativo"));
	            exame.setDsDetalheExame(rs.getString("ds_detalhe_exame"));
	            exame.setDsDetalheExame1(rs.getString("ds_detalhe_exame1"));
	            return exame;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return null;
	}
    
    public List<Exame> listarExames(int pagina, int qtdPagina, String nm_exame, Integer cd_exame, Boolean ic_ativo) {
    	logger.info("pagina: " + pagina);
    	logger.info("qtdPagina: " + qtdPagina);
    	
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
    	
    	try {
    		StringBuilder query = new StringBuilder("SELECT cd_exame, nm_exame, ic_ativo FROM exame WHERE 1=1");
    		
    		List<Object> parametros = buildWhereCondition(nm_exame, cd_exame, ic_ativo, query);

            query.append(" LIMIT ?, ?");
            parametros.add((pagina - 1) * qtdPagina);
            parametros.add(qtdPagina);
    		
            connection = DBUtil.getConnection();            
            preparedStatement = connection.prepareStatement(query.toString());
            
            for (int i = 0; i < parametros.size(); i++) {
                preparedStatement.setObject(i + 1, parametros.get(i));
            }
            
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Exame> exames = new ArrayList<>();
            while (rs.next()) {
                Exame exame = new Exame();
                exame.setCdExame(rs.getInt("cd_exame"));
                exame.setNmExame(rs.getString("nm_exame"));
                exame.setIcAtivo(rs.getBoolean("ic_ativo"));
                exames.add(exame);
            }
            
            return exames;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Exame>();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Exame> listarExamesAtivos() {   	
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
    	
    	try {
    		StringBuilder query = new StringBuilder("SELECT cd_exame, nm_exame, ic_ativo FROM exame WHERE ic_ativo = 1 ORDER BY nm_exame");
    		
            connection = DBUtil.getConnection();            
            preparedStatement = connection.prepareStatement(query.toString());
            
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Exame> exames = new ArrayList<>();
            while (rs.next()) {
                Exame exame = new Exame();
                exame.setCdExame(rs.getInt("cd_exame"));
                exame.setNmExame(rs.getString("nm_exame"));
                exame.setIcAtivo(rs.getBoolean("ic_ativo"));
                exames.add(exame);
            }
            
            return exames;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Exame>();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	private List<Object> buildWhereCondition(String nm_exame, Integer cd_exame, Boolean ic_ativo, StringBuilder query) {
		List<Object> parametros = new ArrayList<>();
		
		if (nm_exame != null && !nm_exame.isEmpty()) {
		    query.append(" AND nm_exame LIKE ?");
		    parametros.add("%" + nm_exame.toUpperCase().trim() + "%");
		}

		if (cd_exame != null && cd_exame > 0) {
		    query.append(" AND cd_exame = ?");
		    parametros.add(cd_exame);
		}

		if (ic_ativo != null) {
		    query.append(" AND ic_ativo = ?");
		    parametros.add(ic_ativo);
		}
		return parametros;
	}
    
    public int contarExames(String nm_exame, Integer cd_exame, Boolean ic_ativo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            StringBuilder query = new StringBuilder("SELECT COUNT(cd_exame) FROM exame WHERE 1=1");
            List<Object> parametros = buildWhereCondition(nm_exame, cd_exame, ic_ativo, query);
            
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
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int  incluirExame(Exame exame) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO exame (nm_exame, ic_ativo, ds_detalhe_exame, ds_detalhe_exame1) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, exame.getNmExame());
            preparedStatement.setBoolean(2, exame.isIcAtivo());
            preparedStatement.setString(3, exame.getDsDetalheExame());
            preparedStatement.setString(4, exame.getDsDetalheExame1());

            int rowsInserted = preparedStatement.executeUpdate();


            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
            
            return -1;
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
    }

    public boolean alterarExame(Exame exame) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE exame SET nm_exame=?, ic_ativo=?, ds_detalhe_exame=?, ds_detalhe_exame1=? WHERE cd_exame=?");

            preparedStatement.setString(1, exame.getNmExame());
            preparedStatement.setBoolean(2, exame.isIcAtivo());
            preparedStatement.setString(3, exame.getDsDetalheExame());
            preparedStatement.setString(4, exame.getDsDetalheExame1());
            preparedStatement.setInt(5, exame.getCdExame());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
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
    }

    public boolean excluirExame(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM exame WHERE cd_exame = ?");
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
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
        
    }
    

}