//
//  registrationViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/8/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit
import Alamofire
class registrationViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate{
    
    @IBOutlet weak var userNameTxtField: UITextField!
    @IBOutlet weak var pwdTxtField: UITextField!
    @IBOutlet weak var genderTxtField: UITextField!
    @IBOutlet weak var veteranTxtField: UITextField!
    @IBOutlet weak var phoneTxtField: UITextField!
    @IBOutlet weak var ageTxtField: UITextField!
    @IBOutlet weak var rolePickerView: UIPickerView!
    @IBOutlet weak var emailTxtField: UITextField!
    var roleTxt: String
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        rolePickerView.delegate = self
        rolePickerView.dataSource = self
        
        let parameters: Parameters = [
            "username": userNameTxtField!,
            "age": pwdTxtField!,
            "gender": genderTxtField!,
            "vet_S": veteranTxtField!,
            "contact": [
                "phone": phoneTxtField!,
                "email": emailTxtField!
            ],
            "password": pwdTxtField!,
            "role": roleTxt,
        ]

        var checker = false
        Alamofire.request("https://she17er.herokuapp.com/api/users/newUsers", method: .post, parameters, encoding: JSONEncoding.default).validate().responseJSON {
            (response) -> Void in
            
            if response.result.value != nil  {
                checker = true
            }
        }
        
        
    }
    
    let roles = ["user", "admin", "shelter"]
    
    @available(iOS 2.0, *)
    public func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1;
    }
    
    
    // returns the # of rows in each component..
    @available(iOS 2.0, *)
    public func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return roles.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return roles[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        roleTxt = roles[row]
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
}
