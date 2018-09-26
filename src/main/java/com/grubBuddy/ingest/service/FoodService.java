package com.grubBuddy.ingest.service;

import com.grubBuddy.ingest.api.FoodCompositionApi;
import com.grubBuddy.ingest.dao.FoodDAO;
import com.grubBuddy.ingest.models.foodApi.FoodApiResponse;
import com.grubBuddy.ingest.models.foodApi.FoodItem;
import com.grubBuddy.ingest.models.foodApi.FoodList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.List;

@Component
public class FoodService {

    @Autowired
    private FoodCompositionApi foodCompositionApi;

    @Autowired
    private FoodDAO foodDAO;


    public void syncFoodsDatabase() throws InterruptedException {
        int page = 0;
        int take = 500;
        final boolean[] isDone = {false};
        final int[] inProcess = {0};

        while(!isDone[0]) {
            if(inProcess[0] != 5) {
                inProcess[0] = inProcess[0] + 1;
                syncFoodAndNutrients(page, take, isDone, inProcess);
                page += take;
            }

            Thread.sleep(0, 1);
        }
    }

    private void syncFoodAndNutrients(int page, int take, boolean[] isDone, int[] inProcess) {
        Mono<Tuple2<FoodApiResponse, FoodApiResponse>> dataSync = foodItemSync(page, take);

        this.foodDAO.insertMany(
                dataSync.map(x -> mapFoodItems(isDone, x))
            )
            .doOnComplete(() -> inProcess[0] = inProcess[0] - 1)
            .doOnError(err ->  {
                System.out.println(err);
                isDone[0] = true;
            })
            .subscribeOn(Schedulers.parallel())
            .subscribe();
    }

    private Mono<Tuple2<FoodApiResponse, FoodApiResponse>> foodItemSync(int page, int take) {
        Mono<FoodApiResponse> syncFoodItems = getFoods(page, take);
        Mono<FoodApiResponse> syncNutrients = getNutrients(page, take);

        return syncFoodItems.zipWith(syncNutrients);
    }

    private Mono<FoodApiResponse> getFoods(int page, int take) {
        return this.foodCompositionApi.getFoodItems(take, page + 1, "f")
                    .defaultIfEmpty(new FoodApiResponse().withList(new FoodList().withItem(new ArrayList<>())));
    }

    private Mono<FoodApiResponse> getNutrients(int page, int take) {
        return this.foodCompositionApi.getFoodItems(take, page + 1, "n")
                    .defaultIfEmpty(new FoodApiResponse().withList(new FoodList().withItem(new ArrayList<>())));
    }

    private List<FoodItem> mapFoodItems(boolean[] isDone, Tuple2<FoodApiResponse, FoodApiResponse> x) {
        if(x == null) {
            isDone[0] = true;
            return null;
        }
        List<FoodItem> items = new ArrayList<>();
        items.addAll(x.getT1().list.foodItems);
        items.addAll(x.getT2().list.foodItems);
        return  items;
    }

}
