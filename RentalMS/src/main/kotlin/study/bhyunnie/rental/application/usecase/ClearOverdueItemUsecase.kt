package study.bhyunnie.rental.application.usecase

import study.bhyunnie.rental.framework.web.dto.ClearOverdueInfoDto
import study.bhyunnie.rental.framework.web.dto.RentalResultOutputDto

interface ClearOverdueItemUsecase {
	fun clearOverdue(clearOverdueInfoDto: ClearOverdueInfoDto): RentalResultOutputDto
}