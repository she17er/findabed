//
//  ShelterCardViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/26/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit
import Alamofire

class ShelterCardViewController: UIViewController {

    var shelterNames:[String] = []
    var shelterAcceptedTypes:[String] = []
    var shelterPhoneNumbers:[String] = []
    var shelterCurrentCapacity:[String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        Alamofire.request("https://she17er.herokuapp.com/api/shelter/getShelters").validate().responseJSON {
            (responseObject) -> Void in
            
            print(responseObject)
            
            if responseObject.result.isSuccess {
                print(responseObject)
            }
            if responseObject.result.isFailure {
                print("error")
            }
        }
    }
    
    
}
