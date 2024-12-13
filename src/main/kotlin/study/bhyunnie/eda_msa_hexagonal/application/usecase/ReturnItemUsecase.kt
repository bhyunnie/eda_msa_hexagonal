package study.bhyunnie.eda_msa_hexagonal.application.usecase

import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.UserItemInputDto

interface ReturnItemUsecase {
	fun returnItem(returnDto: UserItemInputDto): RentalCardOutputDto
}