package com.domo.apparchitecture.injection.components;

import com.domo.apparchitecture.activities.AddBeverageActivity;
import com.domo.apparchitecture.activities.EditBeverageActivity;
import com.domo.apparchitecture.services.BeverageService;

/**
 * Created by dallindyer on 11/17/15.
 */
public interface ApplicationComponent {

    // activities
    void inject(AddBeverageActivity addBeverageActivity);
    void inject(EditBeverageActivity editBeverageActivity);

    BeverageService getBeverageService();

}
