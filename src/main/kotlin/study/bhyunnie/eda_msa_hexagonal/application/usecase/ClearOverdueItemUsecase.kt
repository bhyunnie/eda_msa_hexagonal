package study.bhyunnie.eda_msa_hexagonal.application.usecase

import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.ClearOverdueInfoDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalResultOutputDto

interface ClearOverdueItemUsecase {
	fun clearOverdue(clearOverdueInfoDto: ClearOverdueInfoDto):RentalResultOutputDto
}