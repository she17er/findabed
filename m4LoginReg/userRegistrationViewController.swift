//
//  registrationViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/8/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit
class userRegistrationViewController: UIViewController{
    
    @IBOutlet weak var userNameTxtField: UITextField!
    @IBOutlet weak var pwdTxtField: UITextField!
    @IBOutlet weak var genderTxtField: UITextField!
    @IBOutlet weak var veteranTxtField: UITextField!
 
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        let toolbar = UIToolbar()
        
        let doneButton = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.done, target: self, action: #selector(self.doneClicked))
        
        toolbar.setItems([doneButton], animated: false)
        
        userNameTxtField.inputAccessoryView = toolbar
        pwdTxtField.inputAccessoryView = toolbar
        genderTxtField.inputAccessoryView = toolbar
        veteranTxtField.inputAccessoryView = toolbar
        
    }
    @objc func doneClicked() {
        view.endEditing(true)
    }
    @IBAction func onNextClicked(_ sender: Any) {
        if !userNameTxtField.text!.isEmpty &&
            !pwdTxtField.text!.isEmpty &&
            !genderTxtField.text!.isEmpty &&
            !veteranTxtField.text!.isEmpty {
            performSegue(withIdentifier: "registrationIdentifier", sender: self)
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? userRegistrationContinuedViewController {
            destination.userName = userNameTxtField.text
            destination.password = pwdTxtField.text
            destination.gender = genderTxtField.text
            destination.veteran = veteranTxtField.text
        }
    }
}

