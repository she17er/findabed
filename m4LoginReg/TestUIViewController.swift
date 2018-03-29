//
//  TestUIViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/28/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit

class TestUIViewController: UIViewController {

    
    @IBOutlet weak var actualTextField: UITextField!
    @IBOutlet weak var textView: UIView!
    override func viewDidLoad() {
        
        textView.layer.shadowColor = UIColor.gray.cgColor
        textView.layer.shadowOffset = .zero
        textView.layer.shadowOpacity = 0.6
        textView.layer.shadowRadius = 5.0
        textView.layer.shadowPath = UIBezierPath (rect: textView.bounds).cgPath
        textView.layer.shouldRasterize = true
        
        
        
        actualTextField.leftViewMode = UITextFieldViewMode.always
        let imageView = UIImageView();
        let image = UIImage(named: "Search");
        imageView.image = image;
        imageView.frame = CGRect(x: 0, y: 0, width: (image?.size.width)!, height: (image?.size.height)!)
        actualTextField.leftView = imageView
        actualTextField.backgroundColor = .clear
        
        actualTextField.translatesAutoresizingMaskIntoConstraints = false
        actualTextField.contentHorizontalAlignment = UIControlContentHorizontalAlignment.left
        actualTextField.contentMode = .scaleAspectFit
    }
    

}

