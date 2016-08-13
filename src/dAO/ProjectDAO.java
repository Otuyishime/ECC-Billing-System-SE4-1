package dAO;

import testECC.*;

import java.util.ArrayList;

import javax.persistence.Query;

public class ProjectDAO
{
	public static void saveProject(Project project) {
		EM.getEM().persist(project);
	}
	
	public static int  updateProject(String columnname, String fromvalue, String tovalue) {
		Query query = EM.getEM().createQuery("UPDATE " + Project.class.getName() + " SET " + 
				columnname + " = " + tovalue + " WHERE " + columnname + " = " + fromvalue);
		int success = query.executeUpdate();
		return success;
	}
	
	public static void addProject(Project project) {
		if ( findProjectById(project.getId()) == null){
			EM.getEM().persist(project);
		}
	}

	public static ArrayList<Project> listProjects() {
		Query query = EM.getEM().createQuery("SELECT project FROM " + Project.class.getName() + " project", Project.class);
		ArrayList<Project> list= new ArrayList<Project>(query.getResultList());

		return list;
	}

	public static Project findProjectById(long id) {
		Project project = EM.getEM().find(Project.class, new Long(id));
		return project;
	}

	public static void removeProjectById(long id) {
		Project project = findProjectById(id);
		if ( project != null){
			EM.getEM().remove(project);
			return;
		}
		System.out.println("Unable to delete the client");
	}
}