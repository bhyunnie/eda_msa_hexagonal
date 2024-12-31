package study.bhyunnie.member.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import study.bhyunnie.member.common.LOCAL_URL

@Configuration
class SwaggerConfig {
	@Bean
	fun openApi(): OpenAPI {
		return OpenAPI()
			.components(Components())
			.info(getInfo())
			.servers(setServer())
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