package com.anahuac.calidad.dbunit;

import com.anahuac.calidad.doublesDAO.Alumno;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class DAOAlumnoTest extends DBTestCase {

    public DAOAlumnoTest(){
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:33060/pruebas_db");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "secret");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("src/resources/iniDB.xml"));
    }

    @Before
    public void setUp() throws Exception{
        IDatabaseConnection connection = getConnection();
        try {
            DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
        } catch (Exception e){
            fail("Error in setup: " + e.getMessage());
        } finally {
            connection.close();
        }
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void test() {
        Alumno alumno = new Alumno("Edu", "01", 20, "hellothere@gmail.com");
        AlumnoDAOMySQL daoMySQL = new AlumnoDAOMySQL();

        daoMySQL.addAlumno(alumno);

        try {
            IDataSet databaseDataSet = getConnection().createDataSet(); 
            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");


            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);
        } catch (Exception e) {
            fail("Error in insert test: " + e.getMessage());
        }

    }
}