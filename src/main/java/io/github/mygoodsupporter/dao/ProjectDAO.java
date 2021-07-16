package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Project;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAO {
    @Autowired
    private SqlSessionTemplate sql;

    //프로젝트 정보 insert
    public int projectRequest(Project pdto) {
        return sql.insert("Project.insert", pdto);
    }

    //프로젝트 심사 insert
    public int projectSimsa(Project pdto) {
        return sql.insert("Simsa.insert", pdto);
    }
}
