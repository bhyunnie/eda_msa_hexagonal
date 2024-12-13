package study.bhyunnie.rental.application.usecase

import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.UserItemInputDto

interface ReturnItemUsecase {
	fun returnItem(returnDto: UserItemInputDto): RentalCardOutputDto
}