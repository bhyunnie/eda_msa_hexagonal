package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.EventOutputPort
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.CompensationUsecase
import study.bhyunnie.rental.domain.model.RentalCard
import study.bhyunnie.rental.domain.model.event.PointUseCommand
import study.bhyunnie.rental.domain.model.vo.IDName
import study.bhyunnie.rental.domain.model.vo.Item
import java.util.NoSuchElementException

@Service
@Transactional
class CompensationInputPort(
    private val rentalCardOutputPort: RentalCardOutputPort,
    private val eventOutputPort: EventOutputPort
):CompensationUsecase {
    override fun cancelRentItem(idName: IDName, item: Item): RentalCard {
        val rentalCard = rentalCardOutputPort.loadRentalCard(idName.id).orElseThrow{
            NoSuchElementException("Rental Card Not Found")
        }
        rentalCard.cancelRentItem(item)
        eventOutputPort.occurPointUseCommand(PointUseCommand(idName, 10))
        return rentalCard
    }

    override fun cancelReturnItem(idName: IDName, item: Item, point: Long): RentalCard {
        val rentalCard = rentalCardOutputPort.loadRentalCard(idName.id).orElseThrow {
            NoSuchElementException("Rental card not found")
        }
        rentalCard.cancelReturnItem(item, point)
        eventOutputPort.occurPointUseCommand(PointUseCommand(idName,point))
        return rentalCard
    }

    override fun cancelMakeAvailableRental(idName: IDName, point: Long): Long {
        val rentalCard = rentalCardOutputPort.loadRentalCard(idName.id).orElseThrow {
            NoSuchElementException("Rental card not found")
        }
        return rentalCard.cancelMakeAvailableRental(point)
    }
}