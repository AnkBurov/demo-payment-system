package io.demo.paymentsystem

import io.demo.paymentsystem.datainitializer.DataInitializer
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Lazy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@EntityScan
@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
class Application(
        @Lazy
        @Autowired
        private val dataInitializer: DataInitializer
) : InitializingBean {
    override fun afterPropertiesSet() {
        dataInitializer.createEntities()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}