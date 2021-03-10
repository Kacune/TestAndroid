package app.kacunagi.youruchet;

/**
 * Класс объекта Истории переводов
 */
public class HistoryElem {
    private int scoreId;
    private String scoreName;
    private String sum;
    private String currency;
    private boolean typeTransfer;
    private String comment;
    private Long dateHistory;

    /**
     * Конструктор объекта ИсторииПереводов
     * @param scoreId - Идентификатор Счета
     * @param scoreName - Наименование Счета
     * @param sum - Сумма перевода
     * @param currency - Валюта перевода
     * @param typeTransfer - Тип перевода
     *                     0 - Списание
     *                     1  - Пополнение
     * @param comment - Комментарий к переводу
     * @param dateHistory - Дата перевода
     */
    public HistoryElem(int scoreId, String scoreName, String sum, String currency, boolean typeTransfer, String comment, Long dateHistory) {
        this.scoreId = scoreId;
        this.scoreName = scoreName;
        this.sum = sum;
        this.currency = currency;
        this.typeTransfer = typeTransfer;
        this.comment = comment;
        this.dateHistory = dateHistory;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public Long getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(Long dateHistory) {
        this.dateHistory = dateHistory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(boolean typeTransfer) {
        this.typeTransfer = typeTransfer;
    }
}
