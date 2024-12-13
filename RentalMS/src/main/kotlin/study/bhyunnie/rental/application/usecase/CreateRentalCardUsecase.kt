package study.bhyunnie.rental.application.usecase

import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.UserInputDto

interface CreateRentalCardUsecase {
	fun createRentalCard(userInputDto: UserInputDto): RentalCardOutputDto
}