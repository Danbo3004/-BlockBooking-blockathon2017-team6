package com.github.onefour.blockbooking;

/**
 * Created by onefour on 29.06.17.
 */

public interface GenerationContract {

    interface View extends BaseView<Presenter> {

        void showGeneratedWallet(String walletAddress);
    }

    interface Presenter extends BasePresenter {

        void generateWallet(String password);
        void loadWallet(String password, String walletName);
    }
}
