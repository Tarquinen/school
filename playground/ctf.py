import os
import subprocess
import hashlib

def extract_exe(file_path):
    # Extract the contents of the .exe file using 7zip
    extract_path = file_path.replace('.exe', '_extracted')
    os.makedirs(extract_path, exist_ok=True)
    subprocess.run(['7z', 'x', file_path, f'-o{extract_path}'], check=True)
    print(f"Extracted files to: {extract_path}")
    return extract_path

def find_flag_in_extracted_files(extract_path, target_hashes):
    for root, dirs, files in os.walk(extract_path):
        for file in files:
            file_path = os.path.join(root, file)
            with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                lines = f.readlines()
            for line in lines:
                line = line.strip()  # Remove any leading/trailing whitespace
                hash_object = hashlib.sha256(line.encode())
                generated_hash = hash_object.hexdigest()

                print(f"Checking: '{line}' -> {generated_hash}")
                if generated_hash in target_hashes:
                    print(f"Flag found: {line}")
                    return line

    print("Flag not found in the extracted files.")
    return None

# Example usage
file_path = "C:\\Users\\danny\\test.rep\\AreYouReady.exe"
target_hashes = [
   '629248a843224470ccffb26c701da4cf54cf4fa957fd81dde9200cfa0f375ad1',
   'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'
]

# Extract the exe file
extract_path = extract_exe(file_path)

# Find the flag in the extracted files
find_flag_in_extracted_files(extract_path, target_hashes)

