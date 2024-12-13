package study.bhyunnie.eda_msa_hexagonal.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import study.bhyunnie.eda_msa_hexagonal.common.LOCAL_URL

@Configuration
class SwaggerConfig {
	@Bean
	fun openApi(): OpenAPI {
		val securityRequirement = SecurityRequirement().addList("Bearer Token")
		return OpenAPI()
			.components(Components())
			.info(getInfo())
			.servers(setServer())
			.components(
				Components().addSecuritySchemes(
					"bearer-key",
					SecurityScheme().type(SecurityScheme.Type.HTTP).`in`(SecurityScheme.In.HEADER).name("Authorization")
						.scheme("bearer").bearerFormat("JWT")
				)
			)
			.addSecurityItem(securityRequirement)
	}

	private fun getInfo(): Info {
		return Info()
			.version("1.0.0")
			.description("공부 화이팅")
			.title("Study Hard🔥")
	}

	private fun setServer(): List<Server> {
		return listOf(
			Server().url(LOCAL_URL).description("로컬")
		)
	}
}