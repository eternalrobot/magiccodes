package Core.Config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"Core.db"})
@EnableTransactionManagement
public class DataBaseConfig {
    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource ds=new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/DaiMaShuJu?useSSL=false");//服务器需修改//三个地方修改
        ds.setUsername("root");
        ds.setPassword("qwertyuiop");
        ds.setInitialSize(5);
        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(false);//是否显示hibernate的语句
        adapter.setGenerateDdl(true);//自动创建表的方法
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(BasicDataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("Core.data");
        return emfb;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {//使用persistenceContext注解要加这个bean
        return new PersistenceAnnotationBeanPostProcessor();
    }

    // Spring Data JPA事务配置
    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emfb) {
        JpaTransactionManager jpaTm = new JpaTransactionManager();
        jpaTm.setEntityManagerFactory(emfb.getObject());
        return jpaTm;
    }

}
