package br.org.acal.infra

import br.org.acal.application.legacy.ui.old.screen.principal.TelaPrincipal
import br.org.acal.application.ui.swing.LoginScreen
import com.formdev.flatlaf.FlatDarkLaf
import java.awt.EventQueue
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.lang.NonNull

@SpringBootApplication
@ComponentScan(basePackages = ["br.org.acal**"])
@EnableJpaRepositories(basePackages = ["br.org.acal**"])
@EntityScan(basePackages = ["br.org.acal.resources.model"])
open class AppInitializer(

    private val telaPrincipal: TelaPrincipal,
    private val login: LoginScreen

) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(@NonNull event: ContextRefreshedEvent) {
        EventQueue.invokeLater { telaPrincipal.isVisible = true }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            System.setProperty("java.awt.headless", "false")
            FlatDarkLaf.setup()
            SpringApplication.run(AppInitializer::class.java, *args)
        }
    }
}