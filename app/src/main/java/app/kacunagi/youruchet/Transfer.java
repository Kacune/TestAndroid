package app.kacunagi.youruchet;

import java.util.Date;

/**
 * Класс объекта Перевод
 */
public class Transfer {
    private Score score;
    private Long date;
    private float sum_transfer;
    private String currency;
    private String comment;


    /**
     * Конструктор Перевода
     * @param score - Счет
     * @param date - Дата Перевода
     * @param sum_transfer - Сумма перевода
     * @param currency - Валюта перевода
     * @param comment - Комментарий к переводу
     */
    public Transfer(Score score, Long date, float sum_transfer, String currency, String comment) {
        this.score = score;
        this.date = date;
        this.sum_transfer = sum_transfer;
        this.currency = currency;
        this.comment = comment;
    }

    public Score getScore() {
        return score;
    }

    public Long getDate() {
        return date;
    }

    public float getSum_transfer() {
        return sum_transfer;
    }

    public String getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setSum_transfer(float sum_transfer) {
        this.sum_transfer = sum_transfer;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
