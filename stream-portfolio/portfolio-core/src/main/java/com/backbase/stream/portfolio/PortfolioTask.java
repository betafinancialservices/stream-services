package com.backbase.stream.portfolio;

import com.backbase.stream.portfolio.model.WealthBundle;
import com.backbase.stream.worker.model.StreamTask;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class PortfolioTask extends StreamTask {

    private WealthBundle wealthBundle;

    public PortfolioTask(WealthBundle wealthBundle) {
        super(UUID.randomUUID().toString());
        this.wealthBundle = wealthBundle;
    }

    public PortfolioTask(String taskId, WealthBundle data) {
        super(taskId);
        this.wealthBundle = data;
    }

    public WealthBundle getData() {
        return wealthBundle;
    }

    public PortfolioTask data(WealthBundle portfolioBundle) {
        this.wealthBundle = portfolioBundle;
        return this;
    }

    @Override
    public String getName() {
        return getId();
    }
}
