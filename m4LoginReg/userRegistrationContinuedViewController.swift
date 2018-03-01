//
//  registrationSecondFile.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/14/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit
import Alamofire


class userRegistrationContinuedViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    @IBOutlet weak var ageTxtField: UITextField!
    @IBOutlet weak var phoneTxtField: UITextField!
    @IBOutlet weak var rolePickerView: UIPickerView!
    @IBOutlet weak var emailTxtField: UITextField!
    var roleTxt: String = ""
    
    var userName:String?
    var password:String?
    var gender:String?
    var veteran:String?
    override func viewDidLoad() {
        super.viewDidLoad()
        
        rolePickerView?.delegate = self
        rolePickerView?.dataSource = self
        let toolbar = UIToolbar()

        let doneButton = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.done, target: self, action: #selector(self.doneClicked))
        
        toolbar.setItems([doneButton], animated: false)
        
        ageTxtField.inputAccessoryView = toolbar
        phoneTxtField.inputAccessoryView = toolbar
        emailTxtField.inputAccessoryView = toolbar
        
        
        rolePickerView.selectedRow(inComponent: 0)
        
        
    }
    @objc func doneClicked() {
        view.endEditing(true)
    }
    
    @IBAction func onDoneClicked(_ sender: Any) {
        let parameters: Parameters = [
            "username": userName ?? "",
            "age": ageTxtField.text ?? "",
            "gender": gender ?? "",
            "vet_S": veteran ?? "",
            "contact": [
                "phone": phoneTxtField.text,
                "email": emailTxtField.text
            ],
            "password": password ?? "",
            "role": roleTxt,
            "account_State": "existslmao",
            "login": "true"
            ]
        
        
        Alamofire.request("https://she17er.herokuapp.com/api/users/newUsers", method: HTTPMethod.post, parameters: parameters, encoding: JSONEncoding.default, headers: nil).validate().responseString {
            response in
            print(response)
            
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
