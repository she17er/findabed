//
//  loginViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/8/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class loginViewController: UIViewController {
    
    @IBOutlet weak var nameTxt: UITextField!
    @IBOutlet weak var passwordTxt: UITextField!
    
    
    @IBAction func onSubmitClicked(_ sender: Any) {
        let name = nameTxt.text
        let password = passwordTxt.text
        
        //actually do something with it
        print(name! + password!)
        
        
        
    }
}

