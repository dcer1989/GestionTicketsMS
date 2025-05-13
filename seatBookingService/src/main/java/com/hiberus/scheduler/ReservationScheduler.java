package com.hiberus.scheduler;

    import com.hiberus.usecase.UpdateReservationExpiredUseCase;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.scheduling.annotation.Scheduled;
    import org.springframework.stereotype.Component;

    @Component
    @RequiredArgsConstructor
    @Slf4j
    public class ReservationScheduler {

        private final UpdateReservationExpiredUseCase updateReservationExpiredUseCase;

        @Scheduled(fixedRate = 60000) // Ejecuta cada 60 segundos
        public void scheduleExpiredReservationsCheck() {

            log.info("Executing scheduled task to check expired reservations...");

            updateReservationExpiredUseCase.updateReservationExpired();
        }
    }