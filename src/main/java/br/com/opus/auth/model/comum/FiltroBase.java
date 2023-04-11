package br.com.opus.auth.model.comum;

import br.com.opus.auth.model.EntidadeAPI;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroBase extends EntidadeAPI {

    private Integer currentPage;

    private Integer itensPerPage = 10;

    private Integer totalPages = 1;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null)
            this.currentPage = 1;
        else
            this.currentPage = currentPage;
    }
}