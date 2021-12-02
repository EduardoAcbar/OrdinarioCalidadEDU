package com.anahuac.calidad.doublesDAO;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when; //codigo comentado 


import java.util.HashMap;
import static org.junit.Assert.*;

//EAB
public class FakeDAOTest {

	private FakeDAO dao;
	public HashMap<String, Alumno> alumnos;

	Alumno alumno1;

	@Before
	public void SetUp() throws Exception {
		dao = Mockito.mock(FakeDAO.class);
		alumnos = new HashMap<String, Alumno>();
		alumno1 = new Alumno("001","Nombre",20,  "Dudu@outlook.com");
	}


	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void addAlumnoTest() {
		System.out.println("\t Adding alumno");
		int cuantosAntes = alumnos.size();
		System.out.println("Size Antes : " + cuantosAntes);
		/*
        when(dao.Alumno_add(any(Alumno.class))).thenAnswer(
            new Answer<Boolean>(){
                public Boolean answer(InvocationOnMock invocation) throws Throwable {
                    Alumno arg = (Alumno) invocation.getArguments()[0];
                    alumnos.put("001", arg);
                    System.out.println("Size despues : " + alumnos.size());
                    return true;
                }
            }
        );*/

		doAnswer(new Answer() 
		{
			public Object answer(InvocationOnMock invocation) 
			{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				alumnos.put("001", arg);
				System.out.println("Size despues : " + alumnos.size());
				return null;
			}
		}).when(dao).Alumno_add(alumno1);


		dao.Alumno_add(alumno1);
		int cuantosDespues = alumnos.size();

		assertThat(cuantosAntes + 1, is(cuantosDespues));
	}
	
	
	@Test
	public void deleteAlumnoTest(){
		alumnos.put("001", alumno1);
		System.out.println("\t Delete Alumno");
		int antes_cantidad = alumnos.size();
		System.out.println("Size antes : " + antes_cantidad);

		when(dao.Alumno_delete(any(Alumno.class))).thenAnswer
		(
				new Answer<Boolean>() 
				{
					public Boolean answer(InvocationOnMock invocation) throws Throwable
					{
						Alumno arg = (Alumno) invocation.getArguments()[0];
						alumnos.remove(arg.getID(), arg);
						return true;
					}
				}
				);

		dao.Alumno_delete(alumno1);
		System.out.println("Size despues : " + alumnos.size());
		int despues_cantidad = alumnos.size();
		assertThat(antes_cantidad - 1, is(despues_cantidad));
	}

	@Test
	public void retreiveAlumnoTest() { 			
		System.out.println("\t Retreive Alumno"); 
		when(dao.Alumno_retreive(anyString())).thenAnswer(new Answer<Alumno>() 
		{
			public Alumno answer(InvocationOnMock invocation) throws Throwable
			{
				String arg = (String) invocation.getArguments()[0]; 
				return alumno1; 
			}
		}
				);

		Alumno nuevoAlumno = dao.Alumno_retreive("001");
		assertThat(alumno1.getID(), is(nuevoAlumno.getID()));

		System.out.println("Alumno encontrado"+ "\n"); 
	}


	@Test
	public void updateAlumnoTest() {
		System.out.println("\t Update mail");
		alumnos.put(alumno1.getID(),alumno1);
		String emailBefore= alumno1.getEmail();

		System.out.println("The last email was: "+emailBefore);
		alumno1 = new Alumno("001","Nombre",20, "nuevocorreo@outlook.com");
/*
		when(dao.Email_update(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0]; 
				alumnos.replace(arg.getID(), arg); 
				return null; 
			}
		}
				);*/
		//
		doAnswer(new Answer(){
			public Object answer(InvocationOnMock invocation){
				Alumno arg = (Alumno) invocation.getArguments()[0];
				alumnos.replace(arg.getID(), arg);
				return null;
			}
		}).when(dao).Email_update(any(Alumno.class));
		
		dao.Email_update(alumno1);
		String emailAfter = alumnos.get(alumno1.getID()).getEmail();
		System.out.println("Updated mail : " + emailAfter);
		//compara mails
		assertThat(emailBefore, is(not(emailAfter)));

	}

	
}


