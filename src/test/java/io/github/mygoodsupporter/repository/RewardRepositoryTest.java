package io.github.mygoodsupporter.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SqlGroup({
        @Sql("/fixture/project/project.fixture.sql"),
        @Sql("/fixture/project/reward.fixture.sql")
})
public class RewardRepositoryTest {

    @Test
    void getRewardsByProjectId() {

    }

    @Test
    void getRewardById() {
    }

    @Test
    void saveReward() {
    }

    @Test
    void updateReward() {
    }

    @Test
    void deleteReward() {
    }
}