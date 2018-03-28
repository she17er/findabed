//
//  shelterStruct.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/27/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation

struct Shelter: Codable {
    
    let name: String
    let acceptedTypes: [String]
    let phoneNumber: Int
    let currCapacity: Int
    let coOrdinates: String
    let _id: String
}
