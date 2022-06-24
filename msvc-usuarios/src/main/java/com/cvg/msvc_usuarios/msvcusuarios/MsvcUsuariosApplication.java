package com.cvg.msvc_usuarios.msvcusuarios;

import com.cvg.msvc_usuarios.msvcusuarios.models.entity.Rol;
import com.cvg.msvc_usuarios.msvcusuarios.models.enums.RolName;
import com.cvg.msvc_usuarios.msvcusuarios.models.repository.RolRepository;
import com.cvg.msvc_usuarios.msvcusuarios.service.rol.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableEurekaClient
public class MsvcUsuariosApplication implements CommandLineRunner {

	private final RolRepository rolRepository;

	public MsvcUsuariosApplication(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(MsvcUsuariosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/**List<Rol> roles = Arrays.asList(
				new Rol(0L, RolName.ROLE_ROOT, "Some description"),
				new Rol(0L, RolName.ROLE_WRITER, "Some description"),
				new Rol(0L, RolName.ROLE_READER, "Some description"),
				new Rol(0L, RolName.ROLE_IAM, "Some description")
		);
		this.rolRepository.saveAll( roles );*/
	}
}
