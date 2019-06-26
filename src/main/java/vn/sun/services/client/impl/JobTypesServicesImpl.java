package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.sun.DAO.client.JobTypesDAO;
import vn.sun.entities.JobType;
import vn.sun.services.client.JobTypesServices;

public class JobTypesServicesImpl implements JobTypesServices {
	private static final Logger logger = Logger.getLogger(JobTypesServicesImpl.class);

	@Autowired
	private JobTypesDAO JobTypesDAO;

	public JobTypesDAO getJobTypesDAO() {
		return JobTypesDAO;
	}

	public void setJobTypesDAO(JobTypesDAO jobTypeDAO) {
		this.JobTypesDAO = jobTypeDAO;
	}

	@Override
	public List<JobType> loadJobTypes() {
		try {
			return JobTypesDAO.loadEntities();
		} catch (Exception e) {
			logger.error("has error by loadJobTypes method");
			return null;
		}
	}

}