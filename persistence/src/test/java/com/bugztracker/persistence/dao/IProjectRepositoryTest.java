package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.project.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Oleh_Osyka for EPMC-CLO
 * Date: 14.02.2016
 * Time: 17:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
public class IProjectRepositoryTest {

    @Autowired
    private IProjectRepository projectRepository;

    @Test
    public void testFindByProjectName() throws Exception {
        Project test = new Project();
        test.setName("BT-NURE");
        projectRepository.add(test);
        Assert.assertEquals("BT-NURE", projectRepository.findByProjectName("BT-NURE").getName());
    }
}