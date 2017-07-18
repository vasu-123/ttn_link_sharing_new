package com.ttn.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ttn.dto.TopResourceScore;
import com.ttn.enums.Seriousness;
import com.ttn.enums.Visibility;
import com.ttn.model.LinkResource;
import com.ttn.model.Resource;
import com.ttn.model.ResourceItem;
import com.ttn.model.User;

@Repository("resourceDao")
public class ResourceDaoImpl implements ResourceDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addResource(LinkResource linkResource) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(linkResource);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void setResourceScore(ResourceItem resourceItem) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(resourceItem);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<Resource> getTopResources() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("before");
		Query query = session.createQuery("select resource from ResourceItem ri where ri.resource.topic.visibility=? "
				+ " group by ri.resource order by  avg(score) DESC  ");
		
		query.setParameter(0, Visibility.PUBLIC);
		query.setMaxResults(5);
		//System.out.println(query.list());
		List<Resource> topResources = query.list();
//		System.out.println("After");
System.out.println(topResources.size());
		for(Resource resource : topResources){
			System.out.println("Id : "+resource.getId() + resource.getDescription());
		}
//		System.out.println(topResources);
		return topResources ;
	}

	@Override
	public List<Resource> getRecentResources() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Resource where topic.visibility = ? order by dateCreated DESC  ");
		query.setParameter(0, Visibility.PUBLIC);
		query.setMaxResults(5);
		System.out.println("Private resources : ");
		List<Resource> recentResources = query.list();
		for(Resource resource : recentResources){
			System.out.println(resource.getTopic().getName());
		}
		
		return recentResources;
	}

	
}
