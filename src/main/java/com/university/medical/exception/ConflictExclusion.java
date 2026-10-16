package com.university.medical.exception;

/**
 * Исключение, возникающее при конфликте расписания.
 *
 * <p>Примеры ситуаций:
 * <ul>
 *   <li>Врач уже занят в указанное время</li>
 *   <li>Приём выходит за рабочие часы кабинета</li>
 * </ul>
 */
public class ConflictExclusion extends RuntimeException{

    /**
     * Создаёт исключение с сообщением.
     *
     * @param conflict описание конфликта
     */
    public ConflictExclusion(String conflict) {
        super(conflict);
    }

    /**
     * Создаёт исключение с сообщением и причиной.
     *
     * @param conflict описание конфликта
     * @param cause    причина исключения
     */
    public ConflictExclusion(String conflict, Throwable cause) {
        super(conflict, cause);
    }

}
