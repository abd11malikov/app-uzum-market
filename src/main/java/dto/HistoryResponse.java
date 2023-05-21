package dto;

import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {
  private int userId;
  private String username;
  private String userPhone;
  private int productId;
  private String productName;
  private int paymentId;
  private Timestamp createdDate;

}
