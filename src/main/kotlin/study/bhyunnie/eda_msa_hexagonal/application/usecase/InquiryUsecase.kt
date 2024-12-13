package study.bhyunnie.eda_msa_hexagonal.application.usecase

import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentItemOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.ReturnItemOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.UserInputDto
import java.util.Optional

interface InquiryUsecase {
	fun getRentalCard(userInputDto: UserInputDto): Optional<RentalCardOutputDto>
	fun getAllRentItem(userInputDto: UserInputDto): Optional<List<RentItemOutputDto>>
	fun getAllReturnItem(userInputDto: UserInputDto): Optional<List<ReturnItemOutputDto>>
}