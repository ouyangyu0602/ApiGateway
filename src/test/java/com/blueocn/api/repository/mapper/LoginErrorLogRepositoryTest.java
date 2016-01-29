package com.blueocn.api.repository.mapper;

import com.blueocn.api.BaseTest;
import com.blueocn.api.repository.domain.LoginErrorLogEntity;
import com.blueocn.api.repository.domain.UserEntity;
import com.blueocn.api.support.utils.SystemClock;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class LoginErrorLogRepositoryTest extends BaseTest {

    @Mock
    private UserRepo userRepository;

    @Autowired
    private LoginErrorLogRepo repository;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        UserEntity user = new UserEntity();
        user.setUserEmail("Test Mock");
        Mockito.when(userRepository.queryOne(1L)).thenReturn(user);
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        LoginErrorLogEntity entity = randomEntity(null);
        repository.add(entity);
        LoginErrorLogEntity insert = repository.queryOne(entity.getId());
        Assert.assertTrue(insert.getUserId().equals(entity.getUserId()));
        insert.setUserId(RandomUtils.nextLong(10000, 10000000L));
        repository.update(insert);
        LoginErrorLogEntity insert2 = repository.queryOne(entity.getId());
        Assert.assertTrue(!insert2.getUserId().equals(entity.getUserId()));
        repository.delete(entity.getId());
        LoginErrorLogEntity insert1 = repository.queryOne(entity.getId());
        Assert.assertTrue(insert1 == null);

        Long batchUserId = RandomUtils.nextLong(1000, 1000000L);
        repository.add(randomEntity(batchUserId));
        repository.add(randomEntity(batchUserId));
        repository.add(randomEntity(batchUserId));
        repository.add(randomEntity(batchUserId));
        repository.add(randomEntity(batchUserId));
        repository.add(randomEntity(batchUserId));
        LoginErrorLogEntity logEntity = new LoginErrorLogEntity();
        logEntity.setUserId(batchUserId);
        List<LoginErrorLogEntity> result = repository.query(logEntity);
        Assert.assertTrue(result != null && result.size() == 6);
    }

    @Test
    public void testMock() throws Exception {
        UserEntity entity = userRepository.queryOne(1L);
        Assert.assertTrue("Test Mock".equals(entity.getUserEmail()));
    }

    public LoginErrorLogEntity randomEntity(Long userId) {
        LoginErrorLogEntity entity = new LoginErrorLogEntity();
        entity.setErrorTime(new Date(SystemClock.now()));
        entity.setUserId(userId == null ? RandomUtils.nextLong(1000, 3000000000L) : userId);
        return entity;
    }
}
