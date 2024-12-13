package study.bhyunnie.eda_msa_hexagonal.framework.web.dto

import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard

class UserItemInputDto(
	val userId: String,
	val userName: String,
	val itemId: Long,
	val itemTitle: String
)